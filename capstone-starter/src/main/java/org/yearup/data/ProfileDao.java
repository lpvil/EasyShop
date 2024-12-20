package org.yearup.data;


import org.yearup.models.Profile;

public interface ProfileDao
{
    Profile create(Profile profile);
    Profile getByUserId(int userId);
    Profile update(Profile profile, int userId);
    void delete(int userId);
}
