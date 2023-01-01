package services;

import dao.AdminDAO;
import entities.Admin;

public class AdminService {

    private AdminDAO adminDAO = new AdminDAO();

    public Admin getAdminByField(String field, String Value) {
        return this.adminDAO.findByAnyField(field, Value);
    }

    public boolean updateField(String filterField, String filterValue, String updateField, String updateValue) {
        return this.adminDAO.updateField(filterField, filterValue, updateField, updateValue);
    }
}
