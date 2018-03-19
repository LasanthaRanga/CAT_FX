/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

import java.util.List;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 * @param <T>
 */
public interface SUD<T> {
    
    public boolean isExist(String s);

    public boolean save(T t);

    public boolean update(T t);

    public boolean deactiv(T t);
    
    public List<T> getList();
    
    public T getT_By_Id(int id);
    
    public T getT_By_name(String name);
    
    
    
}
