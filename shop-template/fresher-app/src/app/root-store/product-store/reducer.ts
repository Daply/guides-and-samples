import { initialState, State, featureAdapter } from './state';
import { Actions, SEARCH, SEARCH_COMPLETE, SEARCH_FAILURE } from './actions';


export function featureReducer(state = initialState, action: Actions): State {
    switch (action.type) {
        case SEARCH: {
            return {
                ...state,
                isLoading: true,
                error: null
              };
        }
        case SEARCH_COMPLETE: {
            return featureAdapter.addAll(action.payload.items, {
                ...state,
                isLoading: false,
                error: null
              });
        }
        case SEARCH_FAILURE: {
            return {
                ...state,
                isLoading: false,
                error: action.payload.error
              };
        }
        default: {
            return state;
        }
    }
} 