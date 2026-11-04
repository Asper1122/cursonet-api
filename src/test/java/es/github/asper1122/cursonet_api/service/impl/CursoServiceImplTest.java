package es.github.asper1122.cursonet_api.service.impl;

import es.github.asper1122.cursonet_api.dto.curso.CreateCursoDto;
import es.github.asper1122.cursonet_api.dto.curso.CursoDetailsResponseDto;
import es.github.asper1122.cursonet_api.error.AlreadyExistsException;
import es.github.asper1122.cursonet_api.mapper.CursoMapper;
import es.github.asper1122.cursonet_api.model.Curso;
import es.github.asper1122.cursonet_api.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CursoServiceImplTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoServiceImpl cursoServiceImpl;

    private CreateCursoDto createCursoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        createCursoDto = new CreateCursoDto("Curso de cositas", "");
    }

    @Test
    void testCreateOk() {
        when(cursoRepository.existsByNombre(anyString())).thenReturn(false);
        when(cursoRepository.save(any(Curso.class))).thenReturn(CursoMapper.INSTANCE.toEntity(createCursoDto));

        CursoDetailsResponseDto response = cursoServiceImpl.create(createCursoDto);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(createCursoDto.nombre(), response.nombre()),
                () -> assertEquals(createCursoDto.resumen(), response.resumen()),
                () -> verify(cursoRepository, times(1)).existsByNombre(anyString()),
                () -> verify(cursoRepository, times(1)).save(any(Curso.class))
        );
    }

    @Test
    void testCreateNombreAlreadyExists() {
        when(cursoRepository.existsByNombre(anyString())).thenReturn(true);

        AlreadyExistsException exception =
                assertThrowsExactly(AlreadyExistsException.class, () -> cursoServiceImpl.create(createCursoDto));

        assertAll(
                () -> assertNotNull(exception),
                () -> verify(cursoRepository, times(1)).existsByNombre(anyString()),
                () -> verify(cursoRepository, times(0)).save(any(Curso.class))
        );
    }
}