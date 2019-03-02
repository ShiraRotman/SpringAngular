package rotman.shira.cropx;

import javax.persistence.*;

@Entity @Table(name="votes")
public class ElectionsVote
{
    private Long voteID;
    private ElectionsCampaign campaign;
    private ElectionsUser user,votedUser;

    @Id @Column(name="vote_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getVoteID() { return voteID; }

    private void setVoteID(Long voteID)
    { this.voteID=voteID; }

    @ManyToOne(optional=false)
    @JoinColumn(name="campaign_id",nullable=false)
    public ElectionsCampaign getCampaign() { return campaign; }

    public void setCampaign(ElectionsCampaign campaign)
    { this.campaign=campaign; }

    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    public ElectionsUser getUser() { return user; }

    public void setUser(ElectionsUser user)
    { this.user=user; }

    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="voted_user_id",nullable=false)
    public ElectionsUser getVotedUser() { return votedUser; }

    public void setVotedUser(ElectionsUser votedUser)
    { this.votedUser=votedUser; }
}
