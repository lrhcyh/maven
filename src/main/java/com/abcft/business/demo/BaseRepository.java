package com.abcft.business.demo;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepository implements AbstractRepository {
	
	 /** 
     * 操作mongodb的类,可以参考api 
     */  
    @Autowired  
    private MongoTemplate mongoTemplate;

	@Override
	public void insert(Object obj) {
		mongoTemplate.insert(obj);
	}

	@Override
	public Object findOne(Class<?> entityClass, String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, entityClass);
	}

	@Override
	public List<?> findAll(Class<?> entityClass) {
		return mongoTemplate.find(new Query(), entityClass);
	}

	@Override
	public List<?> findByRegex(Class<?> entityClass, String regex) {
		Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);     
        Criteria criteria = new Criteria("name").regex(pattern.toString());     
          
        return mongoTemplate.find(new Query(criteria), entityClass);
	}

	@Override
	public void removeOne(Class<?> entityClass, String id) {
		Criteria criteria = Criteria.where("_id").in(id);     
		if(criteria == null){     
		     Query query = new Query(criteria);     
		     if(query != null && mongoTemplate.findOne(query, entityClass) != null) {    
		    	 mongoTemplate.remove(mongoTemplate.findOne(query, entityClass));
		     }
		} 
	}

	@Override
	public void removeAll(Class<?> entityClass) {
		List<?> list = this.findAll(entityClass);     
        if(list != null){     
            for(Object obj : list){     
            	mongoTemplate.remove(obj);     
            }     
        } 
	}

	@Override
	public void findAndModify(Class<?> entityClass, String id) {
	} 

}
