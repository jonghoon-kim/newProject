package data;


//import dao.base.StringKeyEntityDao;
import data.base.StringEntityDao;
import entities.Member;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao extends StringEntityDao<Member> {
    //region singleton
        private MemberDao() {
        }
    
        private static MemberDao _instance;
    
        public static MemberDao getInstance(){
            if (_instance == null)
                _instance = new MemberDao();
    
            return _instance;
        }
        //endregion

    @lombok.SneakyThrows
    @Override
    protected Member readEntity(ResultSet resultSet) {
            Member entity = new Member();

            entity.setId(resultSet.getString(1));
            entity.setPassword(resultSet.getString(2));
            entity.setBalance((resultSet.getInt(3)));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from Member";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from Member";
    }

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from Member where Id = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete Member where Id = ?";
    }

    @Override
    public boolean insert(Member entity) {
        //language=TSQL
        String query = "insert into Member values (?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement preparedStatement) {
                preparedStatement.setString(1, entity.getId()); // id 넣는게 맞는지 볼 것
                preparedStatement.setString(2, entity.getPassword());
                preparedStatement.setInt(3, entity.getBalance());
            }
        });
    }

    @Override
    public boolean update(Member entity) {
        //language=TSQL
        String query = "update Member set Password = ?, Balance = ? where Id = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement preparedStatement) {
                preparedStatement.setString(1, entity.getPassword());
                preparedStatement.setInt(2, entity.getBalance());
                preparedStatement.setString(3, entity.getId());
            }
        });
    }
}
