import { connect } from 'react-redux';

import * as actions from '../actions';

import { DownCounter } from '../components';

const mapStateToProps = (state) => ({
  // state는 store에서 관리하는 변수
  // state.{reducer 이름}.{property}
  data: state.downPage.num,
});
const mapDispatchToProps = (dispatch) => ({
  handleDownCount: () => dispatch(actions.downCount()),
});

const DownContainer = connect(mapStateToProps, mapDispatchToProps)(DownCounter);

export default DownContainer;
