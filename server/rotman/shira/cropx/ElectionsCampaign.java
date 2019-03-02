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

    private void setCampaignID(Integer campaignID)
    { this.campaignID=campaignID; }

    @Column(name="campaign_name",nullable=false)
    public String getCampaignName() { return campaignName; }

    private void setCampaignName(String campaignName)
    { this.campaignName=campaignName; }

    @Temporal(TemporalType.DATE)
    @Column(name="start_date",nullable=false)
    public Date getStartDate() { return startDate; }

    private void setStartDate(Date startDate)
    { this.startDate=startDate; }

    @Temporal(TemporalType.DATE)
    @Column(name="end_date",nullable=false)
    public Date getEndDate() { return endDate; }

    private void setEndDate(Date endDate)
    { this.endDate=endDate; }
}
