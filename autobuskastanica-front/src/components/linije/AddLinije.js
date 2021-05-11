import React from 'react';
import AutobuskaStanicaAxios from './../../apis/AutobuskaStanicaAxios';

class AddLinije extends React.Component {

    constructor(props){
        super(props);

        let linija = {
            broj_mesta: 0,
            cena_karte: 0.00,
            vreme_polaska: "",
            destinacija: "",
            prevoznik: null
        }

        this.state = {linija: linija, prevoznici: []}
    }
    componentDidMount(){
        this.getPrevoznici();
    }

    async getPrevoznici(){
        try{
            let result = await AutobuskaStanicaAxios.get("/prevoznici");
            let prevoznici = result.data;
            this.setState({prevoznici: prevoznici});
            console.log("test1");
        }catch(error){
            console.log(error);
            alert("Greska pri pribavljanju prevoznika");
        }
    }

    async create(e){
        e.preventDefault();

        try{
            let linija = this.state.linija;
            let linijaDTO = {
                brroj_mesta: linija.broj_mesta,
                cena_karte: linija.cena_karte,
                vreme_polska: linija.vreme_polaska,
                destinacija: linija.destinacija,
                prevoznikDTO: linija.prevoznik
            }

            let response = await AutobuskaStanicaAxios.post("/linije", linijaDTO);
         this.props.history.push("/linije");
    }catch(error){
        alert("Grska prilkom snimanja linije");
    }
    }

    valueInputChanged(e) {
        let input = e.target;
    
        let name = input.name;
        let value = input.value;
    
        let linija = this.state.linija;
        linija[name] = value;
    
        this.setState({ linija: linija });
    }
    
    prevoznikSelectionChanged(e){

        let prevoznikId = e.target.value;
        let prevoznik = this.state.prevoznici.find((prevoznik) => prevoznik.id == prevoznikId);

        let linija = this.state.linija;
        linija.prevoznik = prevoznik;

        this.setState({linija: linija});
    }



    render(){
        return(
            <div>
                <h1>Dodavanje linije</h1>
                <form>
                    <label htmlFor="pBrojMesta">Broj mesta</label>
                    <input id="pBrojMesta" name="broj_mesta" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    <label htmlFor="pCenaKarte">Cena karte</label>
                    <input id="pCenaKarte" name="cena_karte" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    <label htmlFor="PVremePolsaka">Vreme polaska</label>
                    <input id="PVremePolsaka" name="vreme_polaska" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    <label htmlFor="pDestinacija">Destinacija</label>
                    <input id="pDestinacija" name="destinacija" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                    <label htmlFor="pPrevoznik">Prevoznici</label>
                    
                    <select id="pPrevoznik" onChange={event => this.prevoznikSelectionChanged(event)}>
                        <option></option>
                        {
                            this.state.prevoznici.map((prevoznik) => {
                                return (
                                    <option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>
                                )
                            })
                        }
                    </select><br/>
                    <button onClick={(event)=>{this.create(event);}}>Add</button>

                </form>
            </div>
        );
    }

}

export default AddLinije;