import {
  FETCH_ROWS_ERRORED,
  FETCH_ROWS_LOADING, FETCH_ROWS_SUCCESS,
} from '../enums/actionTypes/form'

export const actionSetParameters = (parameters) => (
  (dispatch) => {
    dispatch(actionFetchRows(parameters));
  }
)


export const actionGetPdf = () => (
  (dispatch) => {
    const url = 'http://localhost:9999/pdf';

    const {parameters } = getState().form.parameters

    let request = new Request(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: Object.keys(parameters).map(param =>
        encodeURIComponent(param) + '=' +
        encodeURIComponent(parameters[param])
      ).join('&'),
    })

    fetch(request)
    .then((response) => {
      if (!response.ok) {
        throw Error(response.statusText);
      }

      dispatch(fetchRowsLoading(false));

      return response;
    })
    .then((response) => response.json())
    .then(rows => dispatch(fetchRowsSuccess(rows)))
    .catch(() => dispatch(fetchRowsError(true)));
  }
)

export const fetchRowsError = (bool) => ({
  type: FETCH_ROWS_ERRORED,
  hasErrored: bool
})

export const fetchRowsLoading = (bool) => ({
  type: FETCH_ROWS_LOADING,
  isLoading: bool
})

export const fetchRowsSuccess = (rows) => ({
  type: FETCH_ROWS_SUCCESS,
  rows
})



export const actionFetchRows = (parameters) => (
  (dispatch) => {
    dispatch(fetchRowsLoading(true));
    dispatch(fetchRowsError(false))

    const url = 'http://localhost:9999/kalkulator';

    let request = new Request(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: Object.keys(parameters).map(param =>
        encodeURIComponent(param) + '=' +
        encodeURIComponent(parameters[param])
      ).join('&'),
    })

    fetch(request)
    .then((response) => {
      if (!response.ok) {
        throw Error(response.statusText);
      }

      dispatch(fetchRowsLoading(false));

      return response;
    })
    .then((response) => response.json())
    .then(rows => dispatch(fetchRowsSuccess(rows)))
    .catch(() => dispatch(fetchRowsError(true)));
  }
);