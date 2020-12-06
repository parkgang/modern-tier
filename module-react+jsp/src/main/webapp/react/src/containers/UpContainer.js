import { connect } from 'react-redux';

import * as actions from '../actions';

import { UpCounter } from '../components';

const mapStateToProps = (state) => ({
  data: state.upPage.tempNum,
});
const mapDispatchToProps = (dispatch) => ({
  handleUpCount: () => dispatch(actions.upCount()),
});

const UpContainer = connect(mapStateToProps, mapDispatchToProps)(UpCounter);

export default UpContainer;
