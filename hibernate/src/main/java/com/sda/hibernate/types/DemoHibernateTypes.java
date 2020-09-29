package com.sda.hibernate.types;

import java.time.LocalDate;

public class DemoHibernateTypes {

    public static void main(String[] args) {
        PlayerDao dao = new PlayerDao();

        Player player = new Player();
        player.setRegistrationDate(LocalDate.now());
        player.setAge(30);
        player.setUsername("jonsnow");
        player.setStatus(Status.ACTIVE);

        dao.create(player);

        // need id of the saved player
        Player foundPlayer = dao.findByUsername(player.getUsername());
        Long foundId = foundPlayer.getId();

        Player playerDetails = new Player();
        playerDetails.setRegistrationDate(LocalDate.now());
        playerDetails.setAge(33);
        playerDetails.setUsername("updated");
        playerDetails.setStatus(Status.INACTIVE);

        dao.update(foundId, playerDetails);
    }
}
