package rotman.shira.cropx;

import java.security.Principal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication @RestController
@ComponentScan("rotman.shira.cropx")
@EntityScan("rotman.shira.cropx")
public class ElectionsService
{
    @PersistenceContext private EntityManager entityManager;

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
        query.setParameter("campaign",campaign); query.setMaxResults(10);
        return query.getResultList().toArray();
    }

    public static void main(String[] args)
    { SpringApplication.run(ElectionsService.class,args); }
}
