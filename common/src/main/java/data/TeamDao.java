package data;

import dao.base.ParameterSetter;
import dao.base.StringKeyEntityDao;
import entities.Team;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDao extends StringKeyEntityDao<Team> {
    //region singleton
        private TeamDao() {
        }

        private static TeamDao _instance;

        public static TeamDao getInstance(){
            if (_instance == null)
                _instance = new TeamDao();

            return _instance;
        }
        //endregion

    @SneakyThrows
    @Override
    protected Team readEntity(ResultSet resultSet) {
            Team entity = new Team();

            entity.setTeam(resultSet.getString(1));
            entity.setWin(resultSet.getInt(2));
            entity.setLose(resultSet.getInt(3));
            entity.setHomeGround(resultSet.getString(4));

            return entity;
    }

    @Override
    protected String getCountQuery() {
            //language=TSQL
            return "Select count(*) from Team";
    }

    @Override
    protected String getAllQuery() {
            //language=TSQL
        return "select * from Team";
    }

    @Override
    protected String getByKeyQuery() {
            //language=TSQL
        return "select * from Team where Team = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
            //language=TSQL
        return "delete Team where Team = ?";
    }

    @Override
    public int insert(Team entity) {
            //language=TSQL
        String query = "insert into Team values (?, ?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, entity.getTeam()); // id 넣는게 맞는지 볼 것
                preparedStatement.setInt(2, entity.getWin());
                preparedStatement.setInt(3, entity.getLose());
                preparedStatement.setString(4, entity.getHomeGround());
            }
        });
    }

    @Override
    public int update(Team entity) {
            //language=TSQL
        String query = "update Team set Win = ?, Lose = ?, HomeGround = ? where Team = ?";

        return execute(query, new ParameterSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, entity.getWin());
                preparedStatement.setInt(2, entity.getLose());
                preparedStatement.setString(3, entity.getHomeGround());
                preparedStatement.setString(4, entity.getTeam());
            }
        });
    }
}
