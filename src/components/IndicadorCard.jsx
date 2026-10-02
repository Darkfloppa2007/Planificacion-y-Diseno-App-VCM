function IndicadorCard({ Valor, Titulo }) {
  return (
    <div className="col-6 col-lg3">
      <div className="card h-100">
        <div className="card-body">
          <h2 className="card-title">{Valor}</h2>
          <p className="card-text fs-1">{Titulo}</p>
        </div>
      </div>
    </div>
  );
}

export default IndicadorCard;