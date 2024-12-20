package org.yearup.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;
//osmig helped with controller
@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {
    private ProfileDao profileDao;
    private UserDao userDao;

    public ProfileController(UserDao userDao, ProfileDao profileDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
    }
    @GetMapping()
    @PreAuthorize("permitAll()")
    @CrossOrigin
    public Profile getById(Principal principal) {
        User user = userDao.getByUserName(principal.getName());

        var profile = profileDao.getByUserId(user.getId());

        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return profile;

    }
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public Profile createUser(@RequestBody Profile profile){
        return profileDao.create(profile);
    }
    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public void update(@RequestBody Profile profile, Principal principal){
        User user = userDao.getByUserName(principal.getName());

        var profile2 = profileDao.getByUserId(user.getId());

        if(profile2 == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        profileDao.update(profile,profile2.getUserId());

    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int id){
        profileDao.delete(id);
    }
}
