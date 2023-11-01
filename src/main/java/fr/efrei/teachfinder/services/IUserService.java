package fr.efrei.teachfinder.services;

import fr.efrei.teachfinder.entities.ApplicationUser;

public interface IUserService {

    ApplicationUser getUser(int userId);

    ApplicationUser updateUser(int userId);
}
