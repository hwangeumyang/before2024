import React from 'react';
import Todo from './MUI_Todo';
import AddTodo from './AddTodo';
import { Paper, List } from "@material-ui/core";
import './App.css';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items : [ ],
        };

    }

    componentDidMount() {
        const requestOptions = {
          method: "GET",
          headers: { 'Content-Type': 'application/json' },
        };
    
        fetch("http://127.0.0.1:8080/todo", requestOptions)
          .then((response) => response.json())
          .then(
            (response) => {
                console.log("test------------------", response);
                this.setState( {
                    items: [response],
                });
            },
            (error) => {
                this.setState({
                    error,
                });
            }
        );
        
        console.log('MUIAPP.componenetDidMount():: state: ', this.state);
    }
    

    
    
    add = (item) => {
        const thisItems = this.state.items;
        item.id = "ID-" + thisItems.length; //key 추가를 위한 id
        item.done = false;
        thisItems.push(item);
        this.setState({item: thisItems});
        console.log("MUIAPP.add():: items: ", this.state.items);
    }

    delete = (item) => {
        const thisItems = this.state.items;
        console.log("MUIAPP.delete():: Before Items : ", this.state.items);
        const newItems = thisItems.filter(e => e.id !== item.id);
        this.setState( { items: newItems }, () => {
            console.log("MUIAPP.delete():: update items: ", this.state.items); //디버깅 콜백
        })


    }

    render() {
        var todoItems = this.state.items.length > 0 && (
            <Paper style = {{margin: 16}}>
                <List>
                    { 
                        this.state.items.map( (item, idx) => (
                            <Todo item={item} key={item.id} delete={this.delete} />
                        ))
                    }
                </List>

            </Paper>
        );

        return (
            <div className="App">
                <AddTodo add={this.add} items={this.state.items} app={this}/>
                {todoItems}
            </div>
        );

    }

}

export default App;