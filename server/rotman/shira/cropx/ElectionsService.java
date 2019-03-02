package rotman.shira.cropx;

import java.util.Date;
import java.util.List;
import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication @RestController
@ComponentScan("rotman.shira.cropx")
@EntityScan("rotman.shira.cropx")
public class ElectionsService
{
    private static final String VOTE_PARTIAL_QUERY=" from ElectionsVote ev join ev.user " +
            "eu join ev.votedUser euv where ev.user=:user and ev.campaign=:campaign";
    @PersistenceContext private EntityManager entityManager;

    private static class VoteData
    {
        public int campaign;
        public String foruser;
    }

    @GetMapping("/user")
    public Principal authUser(Principal user) { return user; }

    @GetMapping("/campaigns")
    public Object[] getAllCampaigns()
    {
        Query query=entityManager.createQuery("select ec from ElectionsCampaign ec");
        return query.getResultList().toArray();
    }

    @GetMapping("/leaders")
    public Object[] getLeadersForCampaign(@RequestParam("campaign") int campaignID)
    {
        ElectionsCampaign campaign=entityManager.find(ElectionsCampaign.class,campaignID);
        if (campaign==null) return null;
        Query query=entityManager.createQuery("select eu.userName,count(ev.votedUser) as votes from " +
                "ElectionsVote ev join ev.votedUser eu where ev.campaign=:campaign group by " +
                "eu.userName order by votes desc");
        query.setParameter("campaign",campaign).setMaxResults(10);
        return query.getResultList().toArray();
    }

    @GetMapping("/vote")
    public Object[] getUserVote(Principal user,@RequestParam("campaign") int campaignID)
    {
        if (user==null) return null;
        Object[] dataObjects=getDataObjects(user,campaignID);
        if (dataObjects==null) return null;
        Query query=entityManager.createQuery("select euv.userID,euv.userName" + VOTE_PARTIAL_QUERY);
        query.setParameter("campaign",dataObjects[0]).setParameter("user",dataObjects[1]);
        List result=query.getResultList();
        if ((result==null)||(result.size()==0)) return new Object[] {0,""};
        else if (result.size()==1) return (Object[])result.get(0);
        else throw new NonUniqueResultException("Duplicate votes!");
    }

    @PostMapping("/vote") @Transactional
    public Object[] changeUserVote(Principal user,@RequestBody VoteData voteData)
    {
        if ((user==null)||(voteData.foruser==null)) return null;
        ElectionsUser votedUser=null;
        if (!voteData.foruser.trim().equals(""))
        {
            TypedQuery<ElectionsUser> userQuery=entityManager.createQuery("select eu from ElectionsUser " +
                    "eu where userName=:username",ElectionsUser.class);
            userQuery.setParameter("username",voteData.foruser);
            List<ElectionsUser> userResult=userQuery.getResultList();
            if ((userResult==null)||(userResult.size()==0)) return null;
            else if (userResult.size()==1) votedUser=userResult.get(0);
            else throw new NonUniqueResultException("Duplicate users!");
        }
        Object[] dataObjects=getDataObjects(user,voteData.campaign);
        if (dataObjects==null) return null;
        ElectionsCampaign campaign=(ElectionsCampaign)dataObjects[0];
        Date now=new Date();
        if ((campaign.getStartDate().compareTo(now)>0)||(campaign.getEndDate().compareTo(now)<0))
            return null;

        TypedQuery<ElectionsVote> voteQuery=entityManager.createQuery("select ev" +
                VOTE_PARTIAL_QUERY,ElectionsVote.class);
        voteQuery.setParameter("campaign",dataObjects[0]).setParameter("user",dataObjects[1]);
        List<ElectionsVote> voteResult=voteQuery.getResultList();
        ElectionsVote vote=null;
        if (voteResult!=null)
        {
            if (voteResult.size()==1) vote=voteResult.get(0);
            else if (voteResult.size()>0) throw new NonUniqueResultException("Duplicate votes!");
        }

        if (votedUser!=null)
        {
            if (vote!=null) vote.setVotedUser(votedUser);
            else
            {
                vote=new ElectionsVote();
                vote.setCampaign(campaign);
                vote.setUser((ElectionsUser)dataObjects[1]);
                vote.setVotedUser(votedUser);
                entityManager.persist(vote);
            }
            return new Object[] { votedUser.getUserID(),votedUser.getUserName() };
        }
        else
        {
            if (vote!=null) entityManager.remove(vote);
            return new Object[] {0,""};
        }
    }

    private Object[] getDataObjects(Principal user,int campaignID)
    {
        ElectionsCampaign campaign=entityManager.find(ElectionsCampaign.class,campaignID);
        if (campaign==null) return null;
        TypedQuery<ElectionsUser> typedQuery=entityManager.createQuery("select eu from ElectionsUser " +
                "eu where userName=:username",ElectionsUser.class);
        typedQuery.setParameter("username",user.getName());
        ElectionsUser voter=typedQuery.getSingleResult();
        return new Object[] {campaign,voter};
    }

    public static void main(String[] args)
    { SpringApplication.run(ElectionsService.class,args); }
}
