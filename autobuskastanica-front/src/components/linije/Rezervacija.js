import React from 'react';
import { Button } from 'react-bootstrap';
import AutobuskaStanicaAxios from './../../apis/AutobuskaStanicaAxios';

class Rezervacija extends React.Component {

    constructor(props){
        super(props);

        this.state = { linijaId: -1, linijaBrojMesta: 0}
           
    }

    componentDidMount(){
        this.getLinijeById(this.props.match.params.id);
    }


    getLinijeById(linijaId){
        AutobuskaStanicaAxios.get('/linije/' + linijaId)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({linijaId: res.data.id});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Greska prilkom ucitavanja!');
         });  
    }

    valueBrojMestaChanged = event => {
        console.log(event.target.value);
        let input = event.target;
        let value = input.value;

        this.setState({
            linijaBrojMesta: value
        }, function(){
            console.log('Broj mesta je ' + this.state.linijaBrojMesta)
            console.log('Id linije je ' + this.state.linijaId)
        })
    }

    rezervacija(event) {
        event.preventDefault();

        AutobuskaStanicaAxios.post('/rezervacije?id=' + this.state.linijaId + '&brojMesta=' + this.state.linijaBrojMesta, {})
        .then(res => {
            // handle success
            console.log(res);
            alert('Uspesna rezervacija');
            this.props.history.push('/linije');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Greska prilkom rezervacije!');
         });
    }
    render(){
        return (
            <div>
                <h1>Rezervacija</h1>
                <form>
                    <label htmlFor="brojMesta">Broj mesta</label> <br/>
                    <input id="brojMesta" type="number" value={this.state.linijaBrojMesta} onChange={(event)=>this.valueBrojMestaChanged(event)}/> <br/>
                    <button className="button button-navy" onClick={(event) => this.rezervacija(event)}>Rezervisi</button>
                </form>
            </div>
        )

    }
}

export default Rezervacija;