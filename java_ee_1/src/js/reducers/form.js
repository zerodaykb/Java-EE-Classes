import {
  FETCH_ROWS_ERRORED,
  FETCH_ROWS_LOADING, FETCH_ROWS_SUCCESS, SET_PARAMETERS,
} from '../enums/actionTypes/form'

const initialState = {
  isLoading: false,
  hasErrored: false,
  parameters: {},
  rows: []
}


export default function formReducer(state = initialState, action) {
  switch (action.type) {
    case FETCH_ROWS_ERRORED:
      return Object.assign({}, state, {
        hasErrored: action.hasErrored
      });

    case FETCH_ROWS_LOADING:
      return Object.assign({}, state, {
        isLoading: action.isLoading
      });

    case FETCH_ROWS_SUCCESS:
      return Object.assign({}, state, {
        rows: action.rows,
      });

    case SET_PARAMETERS:
      return Object.assign({}, state, {
        parameters: action.parameters,
      });

    default:
      return state;
  }
}