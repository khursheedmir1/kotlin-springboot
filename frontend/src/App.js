import React, { useState, useEffect } from 'react';
import { useFlags, withLDProvider } from 'launchdarkly-react-client-sdk';

function Homepage({ ldClient }) {
  const { 'new-homepage': newHomepage } = useFlags();
  const [userKey, setUserKey] = useState('alice');

  useEffect(() => {
    ldClient.identify({ key: userKey });
  }, [userKey, ldClient]);

  const handleConvert = async () => {
    await fetch('http://localhost:4000/convert', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ user: userKey })
    });
    alert(`Conversion tracked for ${userKey}`);
  };

  return (
    <div style={{ textAlign: 'center', marginTop: 40 }}>
      <h2>👤 Current User: {userKey}</h2>
      <button onClick={() => setUserKey('alice')}>Login as Alice</button>
      <button onClick={() => setUserKey('bob')} style={{ marginLeft: 10 }}>Login as Bob</button>

      <div style={{ marginTop: 30 }}>
        {newHomepage ? (
          <h1>🚀 New Homepage Experience</h1>
        ) : (
          <h1>🏠 Old Homepage Experience</h1>
        )}
      </div>

      <button onClick={handleConvert} style={{ marginTop: 20 }}>
        Simulate Conversion
      </button>
    </div>
  );
}

export default withLDProvider({
  clientSideID: 'YOUR_CLIENT_SIDE_ID',
  user: { key: 'alice' }
})(Homepage);