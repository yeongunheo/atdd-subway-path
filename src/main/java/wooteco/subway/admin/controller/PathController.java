package wooteco.subway.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wooteco.subway.admin.dto.request.PathSearchRequest;
import wooteco.subway.admin.dto.response.ShortestPathResponse;
import wooteco.subway.admin.service.PathService;

@RestController
public class PathController {
	private PathService pathService;

	public PathController(PathService pathService) {
		this.pathService = pathService;
	}

	@GetMapping("/paths")
	public ResponseEntity<ShortestPathResponse> findShortestDistancePath(PathSearchRequest pathSearchRequest) {

		ShortestPathResponse shortestPathResponse = pathService.findShortestDistancePath(pathSearchRequest);

		return ResponseEntity.ok()
				.body(shortestPathResponse);
	}
}
