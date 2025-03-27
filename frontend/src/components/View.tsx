import { useEffect, useRef, useState } from 'react';
import Editor from '@monaco-editor/react';

function View() {
  const [code, setCode] = useState('// write JS here\nconsole.log("Hello Minsuk!");');
  const [output, setOutput] = useState('');
  const iframeRef = useRef<HTMLIFrameElement>(null);

  // Run code safely in iframe
  const runCode = () => {
    const iframe = iframeRef.current;
    if (!iframe) return;

    const html = `
      <html>
        <body>
          <script>
            (function() {
              const log = (...args) => {
                parent.postMessage({ type: 'log', message: args.join(' ') }, '*');
              };
              console.log = log;

              try {
                ${code}
              } catch (err) {
                parent.postMessage({ type: 'error', message: err.message }, '*');
              }
            })();
          </script>
        </body>
      </html>
    `;

    setOutput(''); // Clear previous output
    iframe.srcdoc = html; // Inject the user code
  };

  // Listen for logs from iframe
  useEffect(() => {
    const handleMessage = (event: MessageEvent) => {
      if (event.data.type === 'log' || event.data.type === 'error') {
        setOutput(prev => prev + event.data.message + '\n');
      }
    };

    window.addEventListener('message', handleMessage);
    return () => window.removeEventListener('message', handleMessage);
  }, []);

  return (
    <div>
      <Editor
        height="60vh"
        defaultLanguage="javascript"
        value={code}
        onChange={(value) => setCode(value || '')}
      />
      <button onClick={runCode}>Run</button>

      <div
        style={{
          marginTop: '20px',
          whiteSpace: 'pre-wrap',
          backgroundColor: '#f4f4f4',
          padding: '1rem'
        }}
      >
        <strong>Output:</strong>
        <div>{output}</div>
      </div>

      {/* Hidden sandboxed iframe */}
      <iframe
        ref={iframeRef}
        sandbox="allow-scripts"
        style={{ display: 'none' }}
        title="sandbox"
      ></iframe>
    </div>
  );
}

export default View;
