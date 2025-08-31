package com.kh.app08_1.todo;

import com.kh.app08_1.category.CategoryEntity;
import com.kh.app08_1.category.CategoryRepository;
import com.kh.app08_1.state.StateEntity;
import com.kh.app08_1.state.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final StateRepository stateRepository;
    private final CategoryRepository categoryRepository;

    public Long insert(TodoDto dto) {

        StateEntity sEntity = stateRepository.findByNo(dto.getStateNo());
        if (sEntity == null) {
            throw new TodoException("존재하지 않는 상태번호입니다: " + dto.getStateNo());
        }

        CategoryEntity cEntity = categoryRepository.findByNo(dto.getCategoryNo());
        if (cEntity == null) {
            throw new TodoException("존재하지 않는 카테고리번호입니다: " + dto.getCategoryNo());
        }

        TodoEntity entity = TodoEntity.from(dto, sEntity, cEntity);

        todoRepository.insert(entity);
        return entity.getNo();
    }

    public List<TodoDto> list() {
        List<TodoEntity> entityList =  todoRepository.list();
        return entityList.stream().map(TodoDto::from).toList();
    }

    public TodoDto listOne(Long no) {

        TodoEntity entity = Optional.ofNullable(todoRepository.listOne(no))
                .orElseThrow(() -> new TodoException("Todo를 찾을 수 없습니다. id=" + no));
        TodoDto dto = TodoDto.from(entity);
        return dto;
    }

    public Long update(TodoDto dto) {
        TodoEntity entity = Optional.ofNullable(todoRepository.listOne(dto.getNo()))
                .orElseThrow(() -> new TodoException("수정할 Todo가 존재하지 않습니다. id=" + dto.getNo()));

        StateEntity sEntity = stateRepository.findByNo(dto.getStateNo());
        if (sEntity == null) {
            throw new TodoException("존재하지 않는 상태번호입니다: " + dto.getStateNo());
        }

        CategoryEntity cEntity = categoryRepository.findByNo(dto.getCategoryNo());
        if (cEntity == null) {
            throw new TodoException("존재하지 않는 카테고리번호입니다: " + dto.getCategoryNo());
        }

        entity.update(dto, sEntity, cEntity);
        return entity.getNo();
    }

    public Long delete(Long no) {
        TodoEntity entity = Optional.ofNullable(todoRepository.listOne(no))
                .orElseThrow(() -> new TodoException("삭제할 Todo가 존재하지 않습니다. id=" + no));

        entity.delete();
        return entity.getNo();
    }
}
