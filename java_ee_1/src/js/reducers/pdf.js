import {
  GET_PDF_ERRORED,
  GET_PDF_LOADING,
  GET_PDF_SUCCESS,
} from '../enums/actionTypes/pdf'

const initialState = {
  isLoading: false,
  hasErrored: false,
  isFinished: false,
}


export default function pdfReducer(state = initialState, action) {
  switch (action.type) {
    case GET_PDF_ERRORED:
      return Object.assign({}, state, {
        hasErrored: action.hasErrored
      });

    case GET_PDF_LOADING:
      return Object.assign({}, state, {
        isLoading: action.isLoading
      });

    case GET_PDF_SUCCESS:
      return Object.assign({}, state, {
        isFinished: action.isFinished,
      });

    default:
      return state;
  }
}