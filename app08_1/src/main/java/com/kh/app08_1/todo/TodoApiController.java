package com.kh.app08_1.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todo")
public class TodoApiController {

    private final TodoService todoService;

    //생성: 201 Created
    @PostMapping
    public ResponseEntity<Long> insert(@RequestBody TodoDto dto) {
        Long id = todoService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    // 전체 조회: 200 OK
    @GetMapping
    public ResponseEntity<List<TodoDto>> list() {
        List<TodoDto> todos = todoService.list();
        return ResponseEntity.ok(todos);
    }

    // 단일 조회: 200 OK @@@@@@@@@@@ 없으면 TodoException 핸들러 처리
    @GetMapping("{no}")
    public ResponseEntity<TodoDto> listOne(@PathVariable Long no) {
        TodoDto dto = todoService.listOne(no);
        return ResponseEntity.ok(dto);
    }

    // 수정: 200 OK @@@ 수정될 내용없으면 exception 처리댐
    @PutMapping
    public ResponseEntity<Long> update(@RequestBody TodoDto dto) {
        Long updatedId = todoService.update(dto);
        return ResponseEntity.ok(updatedId);
    }

    // 삭제: 204 No Content
    @DeleteMapping("{no}")
    public ResponseEntity<Void> delete(@PathVariable Long no) {
        todoService.delete(no);
        return ResponseEntity.noContent().build();
    }
}
