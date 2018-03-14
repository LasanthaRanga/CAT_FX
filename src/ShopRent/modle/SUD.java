/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShopRent.modle;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 * @param <T>
 */
public interface SUD<T> {

    public boolean save(T t);

    public boolean update(T t);

    public boolean delete(T t);
}
