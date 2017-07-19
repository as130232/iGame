package com.iGame.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.iGame.productOrder.domain.ProductOrder;
import com.iGame.user.domain.User;

@Repository
public class ConditionSpecification {
/*
 * 若 whereCondition 沒有塞選條件 或是找不到該Bean的屬性，將會查詢全部
 */
	public <T> Specification<T> generate(T conditionObject) {
		
		return new Specification<T>() {
			
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				Class c = conditionObject.getClass();
				Method[] methods = c.getMethods();
				
				for( Method method : methods){
					//System.out.println("method:" + method.getName());
					
					//取得getXXX method，getClass並不是getXXX，排除掉此method
					if("get".equals(method.getName().substring(0, 3)) && !"getClass".equals(method.getName())){
						String propertyName = null;
						Boolean hasJoinColumnAnnotation = false;
						String joinColumnName = "";
						try {
							//get property
							Field field = c.getDeclaredField( (method.getName()).substring(3, 4).toLowerCase() + (method.getName()).substring(4));
							//get property name
							propertyName = field.getName();
							System.out.println("propertyName:" + propertyName);
							//get property annotation
							Annotation[] annotations = field.getDeclaredAnnotations();
							//check if the property has @JoinColumn and get column name
							for(Annotation annotation : annotations){
								if("JoinColumn".equals(annotation.annotationType().getSimpleName())){
									hasJoinColumnAnnotation = true;
									joinColumnName = toPropertyName(annotation);
									System.out.println("joinColumnName:" + joinColumnName);
								}
							}
						} catch (NoSuchFieldException | SecurityException e) {
							e.printStackTrace();
						} 
						
						
						Object propertyValue = null;
						Object joinColumnNameValue = null;
						try {
							//call getXXX method and get (whereCondition's property) value
							propertyValue = method.invoke(conditionObject);
							System.out.println("propertyValue:" + propertyValue);
							
							if(hasJoinColumnAnnotation && propertyValue != null){
								//This is a Object what if the property has @JoinColumn, so get one more 
								Class joinColumnClass = propertyValue.getClass();
								Method joinColumnMethod = joinColumnClass.getMethod("get" + firstWordToUpperCase(joinColumnName), null);
								joinColumnNameValue = joinColumnMethod.invoke(propertyValue);
								System.out.println("joinColumnNameValue:" + joinColumnNameValue);
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
						
						if(hasJoinColumnAnnotation && joinColumnNameValue != null){
							predicates.add(cb.equal(root.get(propertyName).get(joinColumnName), joinColumnNameValue));
						}else if(propertyValue != null){
							predicates.add(cb.equal(root.get(propertyName), propertyValue));
						}
					}
				}
				//Predicate[] p = new Predicate[list.size()];  
				return cb.and(predicates.toArray(new Predicate[0]));
			}
			
			/*
			 * 判斷該屬性是否有@JoinColumn的annotation，有的話取得該@JoinColumn(name="XXX") name值(column name)，並轉為對應 property name
			 */
			private Boolean checkJoinColumnAnnotationAndGetJoinColumnName(Annotation[] annotations, String joinColumnName) {
				Boolean hasJoinColumnAnnotation = false;
				for(Annotation annotation : annotations){
					if("JoinColumn".equals(annotation.annotationType().getSimpleName())){
						hasJoinColumnAnnotation = true;
						joinColumnName = toPropertyName(annotation);
						System.out.println("joinColumnName:" + joinColumnName);
					}
				}
				return hasJoinColumnAnnotation;
			}
			
			/*
			 * 將table column name 轉換為 property name
			 * ex: product_id → productId
			 */
			private String toPropertyName(Annotation annotation) {
				String joinColumnName = "";
				String[] array = ((JoinColumn) annotation).name().split("_");
				for(int i = 0; i < array.length; i++){
					if(i > 0){
						//第二輪後，將底線後的字母字首轉為大寫
						array[i] = firstWordToUpperCase(array[i]);
					}
					joinColumnName += array[i];
				}
				return joinColumnName;
			}

			private String firstWordToUpperCase(String word){
				String result = word.toUpperCase().charAt(0) + word.substring(1);
				return result;
			}
	    };
	}
}
