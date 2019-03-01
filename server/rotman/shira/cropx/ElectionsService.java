package rotman.shira.cropx;

import java.security.Principal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication @RestController
@ComponentScan("rotman.shira.cropx")
@EntityScan("rotman.shira.cropx")
public class ElectionsService
{
    @PersistenceContext private EntityManager entityManager;

    @GetMapping("/user")
    public Principal authUser(Principal user) { return user; }

    @GetMapping("/campaign")
    public ElectionsCampaign getCampaign()
    { return entityManager.find(ElectionsCampaign.class,1L); }

    public static void main(String[] args)
    { SpringApplication.run(ElectionsService.class,args); }
}
