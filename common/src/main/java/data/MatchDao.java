package data;


import data.base.StringEntityDao;
import entities.Match;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MatchDao extends StringEntityDao<Match> {

    //region singleton
    private MatchDao() {
    }

    private static MatchDao _instance;

    public static MatchDao getInstance(){
        if (_instance == null)
            _instance = new MatchDao();

        return _instance;
    }

    //endregion

    @Override
    protected String getByKeyQuery() {
        return "select * from Match where matchTime = ?";
    }

    @Override
    protected String deleteByKeyQuery() {

        return "delete betting where matchTime = ?";
    }

    public String getByHomeTeamQuery() { return "select * from Match where homeTeam = ?"; }

    public String getByAwayTeamQuery() { return "select * from Match where awayTeam = ?"; }

    public String getByHomeAllocationQuery() { return "select * from Match where homeAllocation = ?"; }

    public String getByAwayAllocationQuery() { return "select * from Match where awayAllocation = ?"; }

    public String getByWinnerQuery() { return "select * from Match where winner = ?"; }

    @SneakyThrows
    public ArrayList<Match> getByHomeTeam(String homeTeam) {
        //language=TSQL
        String query = getByHomeTeamQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, homeTeam);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Match> getByAwayTeam(String awayTeam) {
        //language=TSQL
        String query = getByAwayTeamQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, awayTeam);
            }
        });
    }

    @SneakyThrows
    public ArrayList<Match> getByHomeAllocation(BigDecimal homeAllocation) {
        //language=TSQL
        String query = getByHomeAllocationQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setBigDecimal(1, homeAllocation);
            }
        });
    }


    @SneakyThrows
    public ArrayList<Match> getByAwayAllocation(BigDecimal awayAllocation) {
        //language=TSQL
        String query = getByAwayAllocationQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setBigDecimal(1, awayAllocation;
            }
        });
    }

    @SneakyThrows
    public ArrayList<Match> getByWinner(String winner) {
        //language=TSQL
        String query = getByWinnerQuery();

        // verbose / decorating code
        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, winner);
            }
        });
    }


    @SneakyThrows
    @Override
    protected Match readEntity(ResultSet result) {
        Match entity = new Match();

        entity.setHomeTeam(result.getString(1));
        entity.setAwayTeam(result.getString(2)); // ->long
        entity.setMatchTime(result.getString(3));
        entity.setHomeAllocation(result.getBigDecimal(4));
        entity.setAwayAllocation(result.getBigDecimal(5));
        entity.setWinner(result.getString(6));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        return "select count(*) from Match";
    }

    @Override
    protected String getAllQuery() {
        return "select * from Match";
    }

    @SneakyThrows
    public boolean insert(Match entity) {
        //language=TSQL
        String query = "insert into Match values (?, ?, ?, ?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getHomeTeam());
                statement.setString(2, entity.getAwayTeam()); // -> long
                statement.setString(3, entity.getMatchTime());
                statement.setBigDecimal(4, entity.getHomeAllocation());
                statement.setBigDecimal(4, entity.getAwayAllocation());
                statement.setString(6, entity.getWinner());
            }
        });
    }

    @SneakyThrows
    public boolean update(Match entity) {
        //language=TSQL
        String query = "update Match set homeTeam = ?," +
                " awayTeam = ?, homeAllocation = ?, awayAllocation = ?," +
                " winner = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getHomeTeam());
                statement.setString(2, entity.getAwayTeam()); // -> long
                statement.setString(3, entity.getMatchTime());
                statement.setBigDecimal(4, entity.getHomeAllocation());
                statement.setBigDecimal(4, entity.getAwayAllocation());
                statement.setString(6, entity.getWinner());
            }
        });
    }
}