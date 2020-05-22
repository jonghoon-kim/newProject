package data;


import data.base.StringEntityDao;
import entities.Match;
import helpers.ConnectionString;
import lombok.SneakyThrows;
import util.ExitStatus;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class MatchDao extends StringEntityDao<Match> {

    //region singleton
    private MatchDao() {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.5;database=Betting;user=sa;password=3512");
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
                statement.setBigDecimal(1, awayAllocation);
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
        entity.setAwayTeam(result.getString(2));
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
                statement.setString(2, entity.getAwayTeam());
                statement.setString(3, entity.getMatchTime());
                statement.setBigDecimal(4, new BigDecimal(0.0));
                statement.setBigDecimal(5, new BigDecimal(0.0));
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
                statement.setString(2, entity.getAwayTeam());
                statement.setString(3, entity.getMatchTime());
                statement.setBigDecimal(4, entity.getHomeAllocation());
                statement.setBigDecimal(5, entity.getAwayAllocation());
                statement.setString(6, entity.getWinner());
            }
        });
    }

    public void calculateBenefit(String matchTime) {

    }
    public ArrayList<Match> showMatch() {

        ArrayList<Match> matches = getByWinner("");
        int i=0;
        for (Match match : matches) {
            i++;
            String str = String.format(" %d. hometeam:%s, awayteam:%s, matchtime:%s," +
                            " homeallocation:%09.1f, awayallocation:%09.1f", i,
                    match.getHomeTeam(), match.getAwayTeam(), match.getMatchTime(), match.getHomeAllocation(),
                    match.getAwayAllocation());
            System.out.println(str);

        }
        return matches;
    }

    public Match selectMatch(ExitStatus exit,ArrayList<Match> matches) {
        Scanner s = new Scanner(System.in);
        System.out.println("베팅하실 경기 번호를 입력하세요. ("+ exit.ESCAPE+" 입력시 베팅 취소)");

        int betting_match = s.nextInt();
        System.out.println("베팅하신 경기:" + betting_match);

        if(betting_match == exit.ESCAPE){   //베팅 포기 탈출 조건
            exit.setEscapeStatus();
            return null;
        }

        if(betting_match > matches.size() || betting_match <= 0) {
            System.out.println("존재하지 않는 경기입니다.");
            return null;
        }

        Match match = getByKey(matches.get(betting_match-1).getMatchTime());
        return match;

    }
}