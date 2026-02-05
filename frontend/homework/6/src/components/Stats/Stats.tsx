import "./Stats.scss";

interface StatsProps {
  total: number;
  available: number;
  unavailable: number;
}

function Stats({ total, available, unavailable }: StatsProps) {
  return (
    <div className="stats">
      <h2>Library Statistics</h2>
      <p>Total Books: {total}</p>
      <p>Available: {available}</p>
      <p>Unavailable: {unavailable}</p>
    </div>
  );
}

export default Stats;
