package com.abcft.business.demo;

import java.util.List;  
import java.util.Set;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.mongodb.core.MongoTemplate;  
import org.springframework.data.mongodb.core.query.Query;  
import org.springframework.stereotype.Repository;  
  

  
/** 
 * 用户DAO 
 * @author huweijun 
 * @date 2016年7月7日 下午8:49:18 
 */  
@Repository  
public class UserDaoImp {  
  
        /** 
         * 操作mongodb的类,可以参考api 
         */  
        @Autowired  
        private MongoTemplate mongoTemplate; 
        /**
         * 为属性自动注入bean服务
         * @param mongoTemplate
         */
        public void setMongoTemplate(MongoTemplate mongoTemplate) {
            this.mongoTemplate = mongoTemplate;
        }
          
        /** 
         * 保存用户信息 
         * @param userBo 
         * @return 
         * @author huweijun 
         * @date 2016年7月7日 下午8:27:37 
         */  
        public UserModel save(UserModel userBo) {  
            mongoTemplate.save(userBo);  
            return userBo;  
        }  
  
        /** 
         * 获取所有集合的名称 
         * @return 
         * @author huweijun 
         * @date 2016年7月7日 下午8:27:28 
         */  
        public Set<String> getCollectionNames() {  
            Set<String> collections = mongoTemplate.getCollectionNames();  
            return collections;  
        }  
          
        /** 
         * 分页查询数据 
         * @param userBo 
         * @param pager 
         * @return 
         * @author huweijun 
         * @date 2016年7月7日 下午8:27:47 
         */  
        public Pager selectPage(UserModel userBo,Pager pager){  
            Query query = new Query();  
            query.skip((pager.getPageNum()-1)*pager.getPageSize());  
            query.limit(pager.getPageSize());  
            /*Criteria criteria = new Criteria(); 
            query.addCriteria(criteria);*/  
            long total = mongoTemplate.count(query, UserModel.class);  
            List<UserModel> users = mongoTemplate.find(query, UserModel.class);  
            pager.setResult(users);  
            pager.setTotal(total);  
            return pager;  
        }  
          
          
}  
  
