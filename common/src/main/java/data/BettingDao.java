package data;

import data.base.IntEntityDao;
import entities.Betting;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BettingDao extends IntEntityDao<Betting> {
        //region singleton
            private BettingDao() {
            }

            private static BettingDao _instance;

            public static BettingDao getInstance(){
                if (_instance == null)
                    _instance = new BettingDao();

                return _instance;
            }

            //endregion

    @Override
    protected String getByKeyQuery() {
        return "select * from betting where bettingNo = ?";
    }

    @Override
    protected String deleteByKeyQuery() {

        return "delete betting where bettingNo = ?";
    }

    @lombok.SneakyThrows
    @Override
    protected Betting readEntity(ResultSet result) {
        Betting entity = new Betting();

        entity.setBettingNo(result.getInt(1));
        entity.setMatchTime(result.getString(2)); // ->long
        entity.setBettingMoney(result.getInt(3));
        entity.setBettingTeam(result.getString("Ulsan"));
        entity.setBettingNo(result.getInt(5));
        return entity;
    }

    @Override
    protected String getCountQuery() {
        return "select count(*) from betting";
    }

    @Override
    protected String getAllQuery() {
        return "select * from betting";
    }

    public boolean insert(Betting entity) {
        //language=TSQL
        String query = "insert into Betting values (?, ?, ?, ?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @lombok.SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getBettingNo());
                statement.setString(2, entity.getMatchTime()); // -> long
                statement.setInt(3, entity.getBettingMoney());
                statement.setString(4, entity.getBettingTeam());
                statement.setInt(5, 0);
                statement.setString(6, entity.getId());
            }
        });
    }

    public boolean update(Betting entity) {
        //language=TSQL
        String query = "update Betting set matchTime = ?, bettingMoney = ?, bettingTeam = ?," +
                " allocationMoney = ?, id = ? where matchTime = ?";

        return execute(query, new ParameterSetter() {
            @lombok.SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getBettingNo());
                statement.setString(2, entity.getMatchTime()); // -> long
                statement.setInt(3, entity.getBettingMoney());
                statement.setString(4, entity.getBettingTeam());
                statement.setInt(5, entity.getAllocationMoney());
                statement.setString(6, entity.getId());


            }
        });
    }
}
