package com.sda.hibernate.queries.native_query;

import java.util.List;
import java.util.logging.Logger;

public class DemoNativeQuery {

    // obtain logger instance
    private static final Logger logger = Logger.getLogger(DemoNativeQuery.class.getName());

    public static void main(String[] args) {
        TraderDao traderDao = new TraderDao();

        // save 2 trader
        // CTRL + P = see method parameters
        Trader trader1 = new Trader("trader1");
        Trader trader2 = new Trader("trader2");

        traderDao.create(trader1);
        traderDao.create(trader2);

        // find all -> list<trader>
        // CTRL + hover = check method signature
        List<Trader> traders = traderDao.findAllWithNativeQuery();
        logger.info("found traders: " + traders);

        List<Trader> filteredTraders = traderDao.findAllByNameWithNamedNativeQuery(trader1.getName());
        logger.info("found filtered traders: " + filteredTraders);

        // expect 2 trader in list
    }
}
