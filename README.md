# Easy Shop online store
<!-- -->
This application is meant to be an online store. You are able to filter by category, color, and you are able to log in to your account and update your information.
This application was created with the help of SpringBoot and applied what we learned in class to achieve desired results.

>Features
<!--line that seperates -->
* See Products
* Filter by category
* Filter by color
* Login
* update user info

-----------
<br>
Home Screen
<br>
<!---->
<br>
<a href="https://ibb.co/dpjsdfn"><img src="https://i.ibb.co/KsbcM5C/Screenshot-2024-12-19-205433.png" alt="Screenshot-2024-12-19-205433" border="0"></a>

<br><br>
>Filtering by category<br>
 ***
<a href="https://imgbb.com/"><img src="https://i.ibb.co/H42SQq9/Screenshot-2024-12-19-205402.png" alt="Screenshot-2024-12-19-205402" border="0"></a><br>
<br>
>Filtering by Color<br>
***
<a href="https://ibb.co/n8xG74F"><img src="https://i.ibb.co/mFfj9d3/Screenshot-2024-12-19-205416.png" alt="Screenshot-2024-12-19-205416" border="0"></a><br>
<br>
>Loging in
***
<a href="https://ibb.co/nDm5fbG"><img src="https://i.ibb.co/Cn8Tvzc/Screenshot-2024-12-19-205450.png" alt="Screenshot-2024-12-19-205450" border="0"></a><br>
<a href="https://imgbb.com/"><img src="https://i.ibb.co/QcXRSbP/Screenshot-2024-12-19-205459.png" alt="Screenshot-2024-12-19-205459" border="0"></a><br>
<br>
>Updating User Information
optional feature
***
<a href="https://ibb.co/gV0Lsfr"><img src="https://i.ibb.co/8cyLFfm/Screenshot-2024-12-19-205506.png" alt="Screenshot-2024-12-19-205506" border="0"><br>
'
<br>
Principal authenticates user/represents currently logged in user<br>
Var Detects dataype of variable
<br>
***



    public Profile getById(Principal principal) {
        User user = userDao.getByUserName(principal.getName());

        var profile = profileDao.getByUserId(user.getId());

        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return profile;
        
        '''
