package com.test.feed;

import com.test.entity.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 胡荆陵
 * 股票模拟数据类
 */
public class MockData {

      public static List<Stock> getData(){
          List<Stock> list = new ArrayList<Stock>();

          list.add(new Stock("600896","中海海盛" , 3.80));
          list.add(new Stock("600606","金丰投资" , 5.78));
          list.add(new Stock("600073","上海梅林" , 6.11));
          list.add(new Stock("600113","浙江东日" , 9.08));
          list.add(new Stock("600515","海岛建设" , 6.24));
          list.add(new Stock("600759","正和股份" , 4.60));
          list.add(new Stock("603993","洛阳钼业" , 7.67));
          list.add(new Stock("600811","东方集团" , 5.26));
          list.add(new Stock("600238","海南椰岛" , 7.47));
          list.add(new Stock("603128","华贸物流" , 6.20));

          return  list;
      }

}
