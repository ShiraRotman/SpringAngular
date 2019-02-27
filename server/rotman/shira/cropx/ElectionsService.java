package rotman.shira.cropx;

import java.util.Map;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication @RestController
@ComponentScan("rotman.shira.cropx")
public class ElectionsService
{
    @GetMapping("campaign")
    public Map<String,Object> getCampaign()
    {
        Map<String,Object> campaignData=new HashMap<>();
        campaignData.put("name","President");
        campaignData.put("startdate",1554768000000L);
        campaignData.put("enddate",1554854400000L);
        return campaignData;
    }

    public static void main(String[] args)
    { SpringApplication.run(ElectionsService.class,args); }
}
