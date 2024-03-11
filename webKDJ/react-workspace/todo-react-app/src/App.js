import React from 'react';
import Todo from './Todo';
import Todo2 from "./Todo2";
import Mock from "./TodoMock";
import MUITodo from "./MUI_Todo";

import './App.css';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        items: [ {id: 0, title: "Hello world 1", done: true},
         {id: 1, title: "Hello world 2", done: false}
        ],
    };

  }
  


  render() {
    const items = this.state.items;

    var todoItems = this.state.items.map(
      (item, idx) => (
        <Todo item={item} key={item.id} />
      )
    );

    // return <div>{todos}</div>;
		return (
			<div className="App">
        {todoItems}
        {/* <MUITodo item = {items[0]}/> */}
			</div>
		);

    
  }

}

export default App;
