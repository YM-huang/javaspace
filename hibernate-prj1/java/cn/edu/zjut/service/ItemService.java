package cn.edu.zjut.service;
import java.util.List;
import java.util.ArrayList;
import cn.edu.zjut.dao.ItemDAO;
public class ItemService {
    private List items = new ArrayList();
    public List getAllItems() {
        ItemDAO dao = new ItemDAO();
        List items = dao.findAll();
        return items;
    }
}
