package wooteco.subway.admin.domain.vo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.WeightedMultigraph;

import wooteco.subway.admin.domain.Line;
import wooteco.subway.admin.domain.LineStation;
import wooteco.subway.admin.domain.LineStationEdge;
import wooteco.subway.admin.domain.Station;

public class Graph {

    private final WeightedMultigraph<Long, LineStationEdge> graph;

    public Graph(WeightedMultigraph<Long, LineStationEdge> graph) {
        this.graph = graph;
    }

    public static Graph of(List<Line> lines, Map<Long, Station> stationMatcher, PathType pathType) {
        WeightedMultigraph<Long, LineStationEdge> graph = new WeightedMultigraph<>(
            LineStationEdge.class);
        addStationsAsVertex(stationMatcher, graph);
        addEdge(lines, pathType, graph);
        return new Graph(graph);
    }

    private static void addEdge(List<Line> lines, PathType pathType,
        WeightedMultigraph<Long, LineStationEdge> graph) {
        for (Line line : lines) {
            addEdgeByLine(pathType, graph, line);
        }
    }

    private static void addEdgeByLine(PathType pathType,
        WeightedMultigraph<Long, LineStationEdge> graph, Line line) {
        for (LineStation lineStation : line.getLineStations()) {
            if (Objects.isNull(lineStation.getPreStationId())) {
                continue;
            }
            addEdge(pathType, graph, lineStation);
        }
    }

    private static void addEdge(PathType pathType, WeightedMultigraph<Long, LineStationEdge> graph,
        LineStation lineStation) {
        LineStationEdge lineStationEdge = LineStationEdge.of(lineStation);
        graph.addEdge(lineStation.getPreStationId(), lineStation.getStationId(),
            lineStationEdge);
        graph.setEdgeWeight(lineStationEdge, pathType.getWeight(lineStation));
    }

    private static void addStationsAsVertex(Map<Long, Station> stationMatcher,
        WeightedMultigraph<Long, LineStationEdge> graph) {
        for (Station station : stationMatcher.values()) {
            graph.addVertex(station.getId());
        }
    }

    public Path getPath(Long source, Long target) {
        DijkstraShortestPath<Long, LineStationEdge> dijkstraShortestPath = new DijkstraShortestPath<>(
            graph);
        GraphPath<Long, LineStationEdge> path = dijkstraShortestPath.getPath(source, target);
        return Path.of(path);
    }
}
