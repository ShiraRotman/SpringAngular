package rotman.shira.cropx;

import java.util.Date;
import javax.persistence.*;

@Entity @Table(name="campaigns")
public class ElectionsCampaign
{
    private Integer campaignID;
    private String campaignName;
    private Date startDate;
    private Date endDate;

    @Id @Column(name="campaign_id") @GeneratedValue
    public Integer getCampaignID() { return campaignID; }

    public void setCampaignID(Integer campaignID)
    { this.campaignID=campaignID; }

    @Column(name="campaign_name")
    public String getCampaignName() { return campaignName; }

    public void setCampaignName(String campaignName)
    { this.campaignName=campaignName; }

    @Temporal(TemporalType.DATE) @Column(name="start_date")
    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate)
    { this.startDate=startDate; }

    @Temporal(TemporalType.DATE) @Column(name="end_date")
    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate)
    { this.endDate=endDate; }
}
