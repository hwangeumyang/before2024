import React from 'react';
import { TextField, Paper, Button, Grid } from "@material-ui/core";

class AddTodo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: { title: "" } }; // object to save user-input
        this.add = props.add;
        this.items = props.items;

        console.log('ADDTodo.constructor():: ', props.items);
    }

    onInputChange = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        // console.log(thisItem);
        // console.log(this.state.item);
        console.log(e);

        this.setState( {item: thisItem });
        console.log('ADDTodo.onInputChange():: ', thisItem);

    }
    
    

    onButtonClick = () => {
        this.add(this.state.item);
        this.setState({item: {title: ""}});
    }
    addItemDirectly = () => {
        // this.items.push(this.state.item);
        const app = this.props.app;
        const item = this.state.item;
        item.id = "ID-" + app.state.items.length; //key 추가를 위한 id
        item.done = false;
        app.state.items.push(this.state.item);
        app.setState(app.state.items)
        this.setState({item: {title: ""}});
    }
    enterKeyEventHandler = (e) => {
        if(e.key == 'Enter') this.onButtonClick();
    }
    render() {

        return (
            <Paper style = {{ margin: 16, padding: 16}}>
                <Grid container>
                    <Grid xs={10} md={10} item style={{ paddingRight: 16}}>
                        <TextField
                            id='TF'
                            placeholder="Add Todo here"
                            fullWidth
                            onChange={this.onInputChange}
                            onKeyPress={this.enterKeyEventHandler}
                            value={this.state.item.title}
                            />
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button
                            fullWidth
                            color="secondary"
                            variant='outlined'
                            onClick={this.onButtonClick}
                        >
                            +
                        </Button>
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button
                            fullWidth
                            color="secondary"
                            variant='outlined'
                            onClick={this.addItemDirectly}
                        >
                            직접
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        );
    }

}

export default AddTodo;