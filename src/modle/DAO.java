/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.util.List;

/**
 *
 * @author suhada
 */
public interface DAO<T> {
    /**
     * 
     * @param t Entity Object
     * @return if success will return true else false
     */
    public boolean save(T t);
    public boolean save(List<T> list);
    /**
     * 
     * @param t Entity Object
     * @return if success will return true else false
     */
    public boolean update(T t);
    public boolean update(List<T> list);
    /**
     * 
     * @param t Entity Object
     * @return if success will return true else false
     */
    public boolean saveOrUpdate(T t);
    public boolean saveOrUpdate(List<T> list);
    /**
     * 
     * @param t Entity Object
     * @return if success will return true else false
     */
    public boolean delete(T t);
    /**
     * 
     * @return return all objects
     */
    public List<T> getList();
}
