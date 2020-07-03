import dao.repositories.UsersRepository;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import web.RegisterServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@RunWith(MockitoJUnitRunner.class)
public class TestRegisterServlet extends Mockito {

  @Spy
  UsersRepository repository = mock(UsersRepository.class);

  @InjectMocks
  private RegisterServlet servlet;

  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private User mockUser;


  @Before
  public void setUp() throws IOException, ServletException {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    session = mock(HttpSession.class);

    when(request.getSession()).thenReturn(session);
    when(request.getParameter("username")).thenReturn("test_user");

    servlet.doPost(request, response);
  }


  @Test
  public void servlet_should_write_info_about_user_session() {
    verify(session).setAttribute(eq("user"), Mockito.any(User.class));
  }

  @Test
  public void servlet_should_add_user_to_repository() {
    verify(repository).add(Mockito.any(User.class));
  }

  @Test
  public void servlet_should_properly_redirect_user() throws IOException {
    verify(response).sendRedirect("profile.jsp");
  }

}
