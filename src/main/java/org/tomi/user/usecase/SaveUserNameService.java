package org.tomi.user.usecase;

public interface SaveUserNameService {

  String saveUserName(String userName);
  //TODO modify this to send the user a list of possibles username if the picked is in use or restricted.
}
