package service;

import model.Developer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import repository.DeveloperRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeveloperServiceTest {
    public static final long TEST_DEVELOPER_ID = 10000L;


    @Mock
    private DeveloperRepository developerRepositoryMock;
    private DeveloperService service;

    @InjectMocks
    DeveloperService developerService = new DeveloperService(developerRepositoryMock);
    ArgumentCaptor<Developer> developerCaptor;
    ArgumentCaptor<Long> idCaptor;
    Developer testDeveloper;
    List<Developer> testDevelopersList;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp(){
        // for initialization developerRepository
        developerRepositoryMock = Mockito.mock(DeveloperRepository.class);
        developerService = new DeveloperService(developerRepositoryMock);

        Mockito.reset(developerRepositoryMock);
        developerCaptor = ArgumentCaptor.forClass(Developer.class);
        idCaptor = ArgumentCaptor.forClass(Long.class);
        testDeveloper = new Developer(TEST_DEVELOPER_ID,"testFirstName", "testLastName");
        testDevelopersList = Arrays.asList(
                new Developer(TEST_DEVELOPER_ID + 1, "firstName1", "lastName1"),
                new Developer(TEST_DEVELOPER_ID + 2, "firstName2", "lastName2"),
                new Developer(TEST_DEVELOPER_ID + 3, "firstName3", "lastName3"));
    }

    @Test
    void save() {
        Mockito.when(developerRepositoryMock.save(testDeveloper)).thenReturn(testDeveloper);
        Developer developer = developerService.save(testDeveloper);
        Mockito.verify(developerRepositoryMock).save(developerCaptor.capture());
        assertEquals(testDeveloper, developerCaptor.getValue());
        assertEquals(TEST_DEVELOPER_ID,developer.getId());

    }

    @Test
    void update() {
        Mockito.when(developerRepositoryMock.save(testDeveloper)).thenReturn(testDeveloper);
        Developer developer = developerService.update(testDeveloper);
        Mockito.verify(developerRepositoryMock).update(developerCaptor.capture());
        assertEquals(testDeveloper, developerCaptor.getValue());
        assertEquals(TEST_DEVELOPER_ID, developerCaptor.getValue().getId());

    }

    @Test
    void getById() {
        Mockito.when(developerRepositoryMock.getById(TEST_DEVELOPER_ID)).thenReturn(testDeveloper);
        Developer currentDeveloper = developerService.getById(TEST_DEVELOPER_ID);
        Mockito.verify(developerRepositoryMock).getById(idCaptor.capture());
        assertEquals(testDeveloper, currentDeveloper);
    }

    @Test
    void deleteById() {
        developerService.deleteById(TEST_DEVELOPER_ID);
        Mockito.verify(developerRepositoryMock).deleteById(idCaptor.capture());
        assertEquals(TEST_DEVELOPER_ID, idCaptor.getValue());
    }

    @Test
    void getAll() {
        Mockito.when(developerRepositoryMock.getAll()).thenReturn(testDevelopersList);
        List<Developer> currentList = developerService.getAll();
        Mockito.verify(developerRepositoryMock).getAll();
        assertEquals(testDevelopersList, currentList);
    }
}
