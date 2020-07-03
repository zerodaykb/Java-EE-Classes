import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import web.LogoutServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class TestLogoutServlet extends Mockito {

  @InjectMocks
  private LogoutServlet servlet;

  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;

  @Before
  public void setUp() throws IOException, ServletException {
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    session = mock(HttpSession.class);

    when(request.getSession()).thenReturn(session);
    servlet.doGet(request, response);
  }


  @Test
  public void servlet_should_destory_user_session() {
    verify(session).invalidate();
  }

  @Test
  public void servlet_should_properly_redirect_user() throws IOException {
    verify(response).sendRedirect("/");
  }

}
