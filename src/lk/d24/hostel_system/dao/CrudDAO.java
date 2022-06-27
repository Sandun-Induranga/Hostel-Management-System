package lk.d24.hostel_system.dao;

import lk.d24.hostel_system.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<Entity extends SuperEntity, ID> extends SuperDAO{
    public boolean save(Entity entity) throws Exception;

    public boolean update(Entity entity) throws Exception;

    public boolean delete(ID id) throws Exception;

    public Entity find(ID id) throws Exception;

    public List<Entity> getAll() throws Exception;
}
