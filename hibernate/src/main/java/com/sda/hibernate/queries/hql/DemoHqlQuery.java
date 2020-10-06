package com.sda.hibernate.queries.hql;

import java.util.List;
import java.util.logging.Logger;

public class DemoHqlQuery {

    private static final Logger logger = Logger.getLogger(DemoHqlQuery.class.getName());

    public static void main(String[] args) {
        // test find all

        // create 2 stocks
        Stock stock1 = new Stock();
        stock1.setName("stock1");
        stock1.setStockCode("S0223");

        Stock stock2 = new Stock();
        stock2.setName("stock2");
        stock2.setStockCode("S0445");

        // save
        StockDao stockDao = new StockDao();
        stockDao.create(stock1);
        stockDao.create(stock2);

        // find all (from db)
        List<Stock> stocks = stockDao.findAllWithHqlQuery();
        stocks.forEach(stock -> logger.info("found stock: " + stock));
        // expect 2 objects in list

        List<Stock> filteredStocks = stockDao.findAllByStockCodeWithNamedQuery(stock1.getStockCode());
        filteredStocks.forEach(stock -> logger.info("found stock: " + stock));
        // expect stock1
    }
}
