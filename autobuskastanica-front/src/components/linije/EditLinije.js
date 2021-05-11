import React from 'react';
import AutobuskaStanicaAxios from './../../apis/AutobuskaStanicaAxios';

class EditLinije extends React.Component{

    constructor(props){
        super(props);

        this.state = { linijaId: -1, 
            broj_mesta: 0, 
            cena_karte: 0, 
            vreme_polaska: "", 
            destinacija: ""}
    }

    componentDidMount(){
        this.getLinijeById(this.props.match.params.id);
    }

    getLinijeById(linijaId){
        AutobuskaStanicaAxios.get('/linije/' + linijaId)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({linijaId: res.data.id, broj_mesta: res.data.brroj_mesta, cena_karte: res.data.cena_karte, vreme_polaska: res.data.vreme_polska, destinacija: res.data.destinacija});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Greska prilkom ucitavanja!');
         });  
    }

    edit() {
        var params = {
            'id': this.state.linijaId,
            'brroj_mesta': this.state.broj_mesta,
            'cena_karte': this.state.cena_karte,
            'vreme_polska': this.state.vreme_polaska,
            'destinacija': this.state.destinacija
        };

        AutobuskaStanicaAxios.put('/linije/' + this.state.linijaId, params)
        .then(res => {
            // handle success
            console.log(res);
            alert('Linija je uspesno dodata!');
            this.props.history.push('/linije');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Greska prilkom izmene!');
         });
    }

    valueDestinacijaChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            destinacija: value
        }));;
    }
    valueVremePolaskaChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            vreme_polaska: value
        }));;
    }

    valueBrojMestaChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            broj_mesta: value
        }));;
    }
    valueCenaKarteChanged = event => {
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            cena_karte: value
        }));;
    }

    render(){
        return(
            <div>
                <h1>Edit linije</h1>
                <form>
                     <label htmlFor="pBrojMesta">Broj mesta</label>
                    <input id="pBrojMesta" type="number" value={this.state.broj_mesta} onChange={(e)=>this.valueBrojMestaChanged(e)}/> <br/>
                    <label htmlFor="pCenaKarte">Cena karte</label>
                    <input id="pCenaKarte" type="number" value={this.state.cena_karte} onChange={(e)=>this.valueCenaKarteChanged(e)}/> <br/>
                    <label htmlFor="PVremePolsaka">Vreme polaska</label>
                    <input id="PVremePolsaka" type="text" value={this.state.vreme_polaska} onChange={(e)=>this.valueVremePolaskaChanged(e)}/> <br/>
                    <label htmlFor="pDestinacija">Destinacija</label>
                    <input id="pDestinacija" type="text" value={this.state.destinacija} onChange={(e)=>this.valueDestinacijaChanged(e)}/> <br/>
                    <button className="button button-navy" onClick={() => this.edit()}>Edit</button>
                    </form>
            </div>
        )
    }


}

export default EditLinije;