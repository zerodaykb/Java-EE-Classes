import {
  FETCH_ROWS_ERRORED,
  FETCH_ROWS_LOADING,
  FETCH_ROWS_SUCCESS, SET_PARAMETERS,
} from '../enums/actionTypes/form'

import {
  GET_PDF_ERRORED,
  GET_PDF_LOADING,
  GET_PDF_SUCCESS,
} from '../enums/actionTypes/pdf'

export const actionSetParameters = (parameters) => (
  (dispatch) => {
    dispatch(actionFetchRows(parameters));

    dispatch({
      type: SET_PARAMETERS,
      parameters: parameters
    })
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

export const getPdfError = (bool) => ({
  type: GET_PDF_ERRORED,
  hasErrored: bool
})

export const getPdfLoading = (bool) => ({
  type: GET_PDF_LOADING,
  isLoading: bool
})

export const getPdfSuccess = (bool) => ({
  type: GET_PDF_SUCCESS,
  isFinished: bool
})

export const actionGetPdf = () => (
  (dispatch, getState) => {
    dispatch(getPdfLoading(true));
    dispatch(getPdfError(false));

    const url = 'http://localhost:9999/pdf';

    const { parameters } = getState().form;

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

      dispatch(getPdfLoading(false));
      dispatch(getPdfSuccess(true));
      return response.blob();
    })
    .then((blob) => {
      //taki hack
      const data = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = data;
      link.download="harmonogram.pdf";
      link.click();
    })
    .catch(() => dispatch(getPdfError(true)));
  }
)


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