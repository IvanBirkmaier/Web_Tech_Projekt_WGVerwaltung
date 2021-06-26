package htw.berlin.wgverwaltung.web;

import htw.berlin.wgverwaltung.persistence.PinWandEntity;

import htw.berlin.wgverwaltung.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PinWallController {
    @Autowired
    private Service service;

    @GetMapping("/findposts")
    public List<PinWandEntity> allePosts(@AuthenticationPrincipal OidcUser user) {
        return service.findAllpinWall(user.getEmail());
    }

    @PostMapping("/posthinzufuegen")
    public PinWandEntity createPost(@AuthenticationPrincipal OidcUser user, @RequestBody PinWandEntity post) {
        post.setOwner(user.getEmail());
        return service.savePinWall(post);
    }

    @DeleteMapping("/postloeschen/{id}")
    public void delete(@PathVariable String id){
        Long pinWallId = Long.parseLong(id);
        service.deletePinWallById(pinWallId);
    }



}
