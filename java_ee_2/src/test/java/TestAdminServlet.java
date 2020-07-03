import dao.repositories.UsersRepository;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import web.AdminServlet;
import web.LoginServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestAdminServlet extends Mockito {

  @Spy
  UsersRepository repository = mock(UsersRepository.class);

  @InjectMocks
  private AdminServlet servlet;

  private HttpServletRequest request;
  private HttpServletResponse response;
  private User mockUser;


  @Before
  public void setUp () throws IOException, ServletException {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);

    mockUser = new User();
    mockUser.setUsername("test_user");
    repository.add(mockUser);

    when(request.getParameter("account_type")).thenReturn("premium");
    when(request.getParameter("username")).thenReturn("test_user");
    when(servlet.retrieveUserFromUsername(request)).thenReturn(mockUser);

    servlet.doPost(request, response);
  }


  @Test
  public void servlet_should_return_user_from_repository () {
    assertEquals(mockUser, servlet.retrieveUserFromUsername(request));
  }

  @Test
  public void servlet_should_properly_redirect_user () throws IOException {
    verify(response).sendRedirect("admin.jsp");
  }

  @Test
  public void servlet_should_update_user () throws IOException {
    verify(repository).update(Mockito.any(User.class));
  }
}